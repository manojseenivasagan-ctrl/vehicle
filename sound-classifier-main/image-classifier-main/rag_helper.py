import os

from langchain_cohere import CohereEmbeddings
from langchain_chroma import Chroma

os.environ["COHERE_API_KEY"] = "4RZMGFGl6RzYko5LktivFU5Qw2FkB5cvMWlZBETu"

embeddings = CohereEmbeddings(
model="embed-english-v3.0",
user_agent="vehicle-doctor"
)

vectordb = Chroma(
persist_directory="vehicle_db",
embedding_function=embeddings
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