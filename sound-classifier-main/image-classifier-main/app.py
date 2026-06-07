import os
import numpy as np
import librosa
import pickle

from flask import Flask, render_template, request
from tensorflow.keras.models import load_model

# RAG Helper

from rag_helper import get_repair_details

# -----------------------------------

# Flask App

# -----------------------------------

app = Flask(__name__)

UPLOAD_FOLDER = "uploads"
os.makedirs(UPLOAD_FOLDER, exist_ok=True)

# -----------------------------------

# Load Model & Label Encoder

# -----------------------------------

MODEL_PATH = "audio_models.h5"
LE_PATH = "labelencoder.pkl"

model = load_model(MODEL_PATH)

with open(LE_PATH, "rb") as f:
    labelencoder = pickle.load(f)

# -----------------------------------

# Audio Parameters

# -----------------------------------

SAMPLE_RATE = 22050
DURATION = 3
N_MFCC = 40

# -----------------------------------

# Fault Mapping

# -----------------------------------

fault_info = {
    "Car": {
        "cause": "Timing chain wear detected",
        "severity": "High",
        "probability": "89%"
    },

    "Bus": {
        "cause": "Brake pad wear detected",
        "severity": "Critical",
        "probability": "94%"
    },

    "Trucks": {
        "cause": "Drive belt deterioration detected",
        "severity": "Medium",
        "probability": "87%"
    },

    "Motocycles": {
        "cause": "Wheel bearing wear detected",
        "severity": "Medium",
        "probability": "85%"
    },

    "Train": {
        "cause": "Traction motor vibration anomaly",
        "severity": "High",
        "probability": "92%"
    },

    "airplane": {
        "cause": "Combustion imbalance detected",
        "severity": "Critical",
        "probability": "96%"
    },

    "helicopter": {
        "cause": "Rotor gearbox vibration anomaly",
        "severity": "Critical",
        "probability": "95%"
    },

    "Bics": {
        "cause": "Chain and brake system wear detected",
        "severity": "Low",
        "probability": "81%"
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

# Home Route

# -----------------------------------

@app.route("/", methods=["GET", "POST"])
def index():

    prediction = None
    confidence = None
    cause = None
    rag_result = None

    if request.method == "POST":

        if "audiofile" not in request.files:
            return render_template("index.html")

        audiofile = request.files["audiofile"]

        if audiofile.filename == "":
            return render_template("index.html")

        audio_path = os.path.join(
            UPLOAD_FOLDER,
            audiofile.filename
        )

        audiofile.save(audio_path)

        try:
            features = extract_features(audio_path)

            preds = model.predict(features, verbose=0)

            class_index = np.argmax(preds)

            confidence = round(
                float(np.max(preds) * 100),
                2
            )

            prediction = labelencoder.inverse_transform(
                [class_index]
            )[0]

            if prediction in fault_info:

                cause = fault_info[prediction]["cause"]

                rag_result = get_repair_details(
                    vehicle=prediction,
                    cause=cause
                )
                
                print("\n===== RAG RESULT =====")
                print(rag_result)
                print("======================\n")

            else:
                cause = "Unknown"
                rag_result = "No repair information found."

        except Exception as e:
            rag_result = f"Error: {str(e)}"

        finally:
            if os.path.exists(audio_path):
                os.remove(audio_path)

    return render_template(
        "index.html",
        prediction=prediction,
        confidence=confidence,
        cause=cause,
        rag_result=rag_result
    )


# -----------------------------------

# Run Flask App

# -----------------------------------

if __name__ == "__main__":
    app.run(
        debug=True,
        host="0.0.0.0",
        port=3000
    )
