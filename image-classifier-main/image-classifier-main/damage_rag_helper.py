import os
from langchain_cohere import CohereEmbeddings
from langchain_chroma import Chroma
from langchain_community.document_loaders import TextLoader
from langchain_text_splitters import RecursiveCharacterTextSplitter

COHERE_API_KEY = os.getenv("COHERE_API_KEY")
GUIDE_PATH = "vehicle_repair_guide.txt"

embeddings = CohereEmbeddings(
    model="embed-english-v3.0",
    cohere_api_key=COHERE_API_KEY,
    user_agent="vehicle-doctor"
)

# Global database parameters
vectordb = None
retriever = None

try:
    # Check if a built database already exists, if not, generate it from the text file
    if os.path.exists("damage_db") and len(os.listdir("damage_db")) > 0:
        print("[RAG INFO] Loading existing persistent damage_db from disk...")
        vectordb = Chroma(
            persist_directory="damage_db",
            embedding_function=embeddings
        )
    elif os.path.exists(GUIDE_PATH):
        print(f"[RAG INFO] 'damage_db' empty or missing. Building fresh in-memory index from {GUIDE_PATH}...")
        loader = TextLoader(GUIDE_PATH)
        documents = loader.load()
        
        text_splitter = RecursiveCharacterTextSplitter(chunk_size=500, chunk_overlap=50)
        docs = text_splitter.split_documents(documents)
        
        vectordb = Chroma.from_documents(docs, embeddings)
    else:
        print(f"[RAG WARNING] Reference file '{GUIDE_PATH}' not found. Initializing empty fallback database.")
        vectordb = Chroma(embedding_function=embeddings)

    if vectordb is not None:
        retriever = vectordb.as_retriever(search_kwargs={"k": 1})

except Exception as e:
    print(f"[RAG ERROR] Critical failure during VectorDB setup: {str(e)}")

# --------------------------------------------------
# Safe Execution Entrypoint
# --------------------------------------------------
import os

FILE_PATH = "damage_repair_guide.txt"

def get_damage_repair_details(label):
    """
    Line-agnostic text block reader. Safely pulls down target segments from 
    damage_repair_guide.txt without crashing on blank lines or formatting shifts.
    """
    query_str = str(label).strip().lower() if label is not None else "unknown"
    print(f"[FILE PARSER LOG] Scanning {FILE_PATH} for damage profile: '{query_str}'")

    if not os.path.exists(FILE_PATH):
        return f"### AI Repair Recommendation\n\nError: Manual file '{FILE_PATH}' is missing."

    try:
        with open(FILE_PATH, "r", encoding="utf-8") as f:
            content = f.read()

        # Split using your equal sign block delimiter
        blocks = content.split("===================================================")
        
        for block in blocks:
            block_stripped = block.strip()
            
            # Verify if this specific section belongs to our target label
            if f"damage type: {query_str}" in block_stripped.lower():
                # Process the text into beautiful Markdown lines cleanly
                lines = [line.strip() for line in block_stripped.split("\n")]
                formatted_markdown = f"### AI Repair Guidelines (Detected: {query_str.upper()})\n\n"
                
                for line in lines:
                    if not line:
                        continue
                    
                    # Highlight important categories
                    if any(line.startswith(k) for k in ["Damage Type:", "Cause:", "Repair Procedure:", "Estimated Cost:", "Repair Time:", "Severity:", "Maintenance Tips:"]):
                        formatted_markdown += f"\n**{line}**\n"
                    # Format structural lists nicely
                    elif line[0].isdigit() if line else False:
                        formatted_markdown += f"{line}\n"
                    else:
                        formatted_markdown += f"{line}  \n" # Double trailing space for Markdown line breaks
                
                return formatted_markdown

        return f"### AI Repair Recommendation\n\nNo manual sections found matching the signature label: `{query_str}`."

    except Exception as e:
        print(f"[PARSER EXCEPTION] Failed to safely parse text files: {str(e)}")
        return f"### AI Repair Recommendation\n\nError processing repair manual context file: {str(e)}"