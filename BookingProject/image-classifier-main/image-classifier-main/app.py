# -----------------------------------
# app.py
# -----------------------------------

import os
import numpy as np
import librosa
import pickle
from flask import Flask, render_template, request
from tensorflow.keras.models import load_model

# -----------------------------------
# Flask App
# -----------------------------------
app = Flask(__name__)

UPLOAD_FOLDER = "uploads"
os.makedirs(UPLOAD_FOLDER, exist_ok=True)

# -----------------------------------
# Load Model & LabelEncoder
# -----------------------------------
MODEL_PATH = "audio_models.h5"
LE_PATH = "labelencoder.pkl"

model = load_model(MODEL_PATH)

with open(LE_PATH, "rb") as f:
    labelencoder = pickle.load(f)

# -----------------------------------
# Audio Parameters (MUST match training)
# -----------------------------------
SAMPLE_RATE = 22050
DURATION = 3
N_MFCC = 40

# -----------------------------------
# Fault Information
# -----------------------------------
fault_info = {
    "airplane": {
        "cause": "Low octane fuel or incorrect ignition timing",
        "repair": "Use recommended fuel and check ignition timing"
    },
    "Bics": {
        "cause": "Faulty spark plug or injector",
        "repair": "Replace spark plug or clean injector"
    },
    "Car": {
        "cause": "Loose or worn timing chain",
        "repair": "Replace timing chain or tensioner"
    },
    "helicopter": {
        "cause": "Cracked exhaust manifold or gasket",
        "repair": "Replace exhaust gasket or repair pipe"
    },
    "Motocycles": {
        "cause": "Worn engine or wheel bearings",
        "repair": "Inspect and replace bearings"
    },
    "Train": {
        "cause": "Incorrect valve clearance or low oil pressure",
        "repair": "Adjust valve clearance and change oil"
    },
    "Trucks": {
        "cause": "Loose or worn belt",
        "repair": "Replace or tighten belt"
    },
    "Bus": {
        "cause": "Worn brake pads",
        "repair": "Replace brake pads"
    }
}

# -----------------------------------
# Feature Extraction
# -----------------------------------
def extract_features(file_path):
    audio, sr = librosa.load(
        file_path,
        sr=SAMPLE_RATE,
        duration=DURATION,
        mono=True
    )

    mfcc = librosa.feature.mfcc(
        y=audio,
        sr=sr,
        n_mfcc=N_MFCC
    )

    mfcc = np.mean(mfcc.T, axis=0)
    return mfcc.reshape(1, -1)

# -----------------------------------
# Routes
# -----------------------------------
@app.route("/", methods=["GET", "POST"])
def index():
    prediction = None
    confidence = None
    cause = None
    repair = None

    if request.method == "POST":
        audiofile = request.files["audiofile"]

        if audiofile.filename == "":
            return render_template("index.html")

        audio_path = os.path.join(UPLOAD_FOLDER, audiofile.filename)
        audiofile.save(audio_path)

        # Extract features
        features = extract_features(audio_path)

        # Predict
        preds = model.predict(features)
        class_index = np.argmax(preds)
        confidence = round(float(np.max(preds) * 100), 2)

        # ✅ CORRECT CLASS DECODING
        prediction = labelencoder.inverse_transform([class_index])[0]

        cause = fault_info[prediction]["cause"]
        repair = fault_info[prediction]["repair"]

        os.remove(audio_path)

    return render_template(
        "index.html",
        prediction=prediction,
        confidence=confidence,
        cause=cause,
        repair=repair
    )

# -----------------------------------
# Run Server
# -----------------------------------
if __name__ == "__main__":
    app.run(debug=True, port=3000)
