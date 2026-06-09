FROM python:3.10-slim

WORKDIR /app

RUN apt-get update && apt-get install -y \
    gcc g++ build-essential \
    libgl1 libglib2.0-0 \
    && rm -rf /var/lib/apt/lists/*

RUN pip install --no-cache-dir --upgrade pip setuptools wheel

COPY requirements.txt .

# install torch first (avoids dependency issues)
RUN pip install --no-cache-dir torch torchvision --index-url https://download.pytorch.org/whl/cpu

RUN pip install --no-cache-dir -r requirements.txt

# ADD THIS LINE: Explicitly bake the reference guide into the image layers
COPY damage_repair_guide.txt /app/damage_repair_guide.txt

COPY . .

EXPOSE 4000

CMD ["python", "app.py"]