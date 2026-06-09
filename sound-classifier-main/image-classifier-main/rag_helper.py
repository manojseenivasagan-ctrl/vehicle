import os
from langchain_core.documents import Document
from langchain_cohere import CohereEmbeddings
from langchain_chroma import Chroma

COHERE_API_KEY = os.getenv("COHERE_API_KEY")

if not COHERE_API_KEY:
    raise ValueError(
        "COHERE_API_KEY environment variable not found"
    )

# 1. Initialize Embeddings Engine
embeddings = CohereEmbeddings(
    model="embed-english-v3.0",
    cohere_api_key=COHERE_API_KEY,
    user_agent="vehicle-doctor"
)

# 2. Automatically load raw documents into RAM at startup
file_path = "vehicle_repair_guide.txt"
documents = []

if os.path.exists(file_path):
    with open(file_path, "r", encoding="utf-8") as f:
        content = f.read()
    
    records = content.split("===================================================")
    for record in records:
        record = record.strip()
        if record:
            documents.append(Document(page_content=record))
    print(f"[RAG INFO] Loaded {len(documents)} documents from text file.")
else:
    print(f"[RAG WARNING] {file_path} not found! Starting with fallback context.")
    documents.append(Document(page_content="Default vehicle engine sound repair guide."))

# 3. Create an Ephemeral In-Memory Database (No persist_directory!)
vectordb = Chroma.from_documents(
    documents=documents,
    embedding=embeddings
)

retriever = vectordb.as_retriever(
    search_kwargs={"k": 1}
)

def get_repair_details(vehicle, cause):
    query = f"{vehicle} {cause}"
    docs = retriever.invoke(query)

    print("Query:", query)
    print("Retrieved Docs:", len(docs))

    for d in docs:
        print("-----")
        print(d.page_content)

    result = "\n\n".join(
        [doc.page_content for doc in docs]
    )

    return result