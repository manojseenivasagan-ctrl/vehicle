import os

from langchain_core.documents import Document
from langchain_cohere import CohereEmbeddings
from langchain_chroma import Chroma

COHERE_API_KEY = os.getenv("COHERE_API_KEY")

if not COHERE_API_KEY:
    raise ValueError(
        "COHERE_API_KEY environment variable not found"
    )

print("Current Folder:", os.getcwd())
print("TXT Exists:", os.path.exists("vehicle_repair_guide.txt"))

file_path = r"D:/ideas/sound-classifier-main/image-classifier-main/vehicle_repair_guide.txt"

with open(file_path, "r", encoding="utf-8") as f:
    content = f.read()

records = content.split(
    "==================================================="
)

documents = []

for record in records:
    record = record.strip()

    if record:
        documents.append(
            Document(page_content=record)
        )

print("Documents Loaded:", len(documents))

embeddings = CohereEmbeddings(
    model="embed-english-v3.0",
    cohere_api_key=COHERE_API_KEY,
    user_agent="vehicle-doctor"
)

vectordb = Chroma.from_documents(
    documents=documents,
    embedding=embeddings,
    persist_directory="vehicle_db"
)

print("Vehicle DB Created Successfully")