import os

from langchain_cohere import CohereEmbeddings
from langchain_chroma import Chroma

COHERE_API_KEY = os.getenv("COHERE_API_KEY")

embeddings = CohereEmbeddings(
    model="embed-english-v3.0",
    user_agent="vehicle-doctor"
)

vectordb = Chroma(
    persist_directory="damage_db",
    embedding_function=embeddings
)

retriever = vectordb.as_retriever(
    search_kwargs={"k": 1}
)

def get_damage_repair_details(label):

    docs = retriever.invoke(label)

    if docs:
        return docs[0].page_content

    return "No repair information found."