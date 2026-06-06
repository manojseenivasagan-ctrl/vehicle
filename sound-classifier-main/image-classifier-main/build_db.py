import os

from langchain_core.documents import Document
from langchain_cohere import CohereEmbeddings
from langchain_chroma import Chroma

os.environ["COHERE_API_KEY"] = "4RZMGFGl6RzYko5LktivFU5Qw2FkB5cvMWlZBETu"

import os

print("Current Folder:", os.getcwd())
print("TXT Exists:", os.path.exists("vehicle_repair_guide.txt"))

file_path = r"D:/ideas/vehicle/sound-classifier-main/image-classifier-main/vehicle_repair_guide.txt"

with open(file_path, "r", encoding="utf-8") as f:
    content = f.read()
    

# Split records
records = content.split("===================================================")

documents = []

for record in records:
    record = record.strip()

    if record:
        documents.append(
            Document(page_content=record)
        )

print("Documents Loaded:", len(documents))

# Embeddings
embeddings = CohereEmbeddings(
    model="embed-english-v3.0",
    user_agent="vehicle-doctor"
)

# Create Vector DB
vectordb = Chroma.from_documents(
    documents=documents,
    embedding=embeddings,
    persist_directory="vehicle_db"
)

print("Vehicle DB Created Successfully")