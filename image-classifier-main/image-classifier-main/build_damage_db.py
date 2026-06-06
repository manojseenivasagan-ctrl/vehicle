from langchain_core.documents import Document
from langchain_cohere import CohereEmbeddings
from langchain_chroma import Chroma
import os

os.environ["COHERE_API_KEY"] = "4RZMGFGl6RzYko5LktivFU5Qw2FkB5cvMWlZBETu"
file_path = r"D:/ideas/vehicle/image-classifier-main/image-classifier-main/damage_repair_guide.txt"

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

embeddings = CohereEmbeddings(
    model="embed-english-v3.0",
    user_agent="vehicle-doctor"
)

Chroma.from_documents(
    documents=documents,
    embedding=embeddings,
    persist_directory="damage_db"
)

print("Damage Database Created")