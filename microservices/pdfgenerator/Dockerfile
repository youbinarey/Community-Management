FROM python:3.12

WORKDIR /app

RUN apt-get update && apt-get install -y \
    libfreetype-dev \
    libjpeg-dev \
    libart-2.0-dev \
    zlib1g-dev \
    && rm -rf /var/lib/apt/lists/*
COPY . .

RUN pip install --upgrade pip
RUN pip install --no-cache-dir -r requirements.txt && pip show flask

CMD ["python", "app.py"]
