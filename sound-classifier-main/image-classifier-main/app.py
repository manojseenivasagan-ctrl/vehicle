import os
import numpy as np
import librosa
import pickle
import tempfile

from flask import Flask, request, jsonify
from flask_cors import CORS
from tensorflow.keras.models import load_model

from rag_helper import get_repair_details

# ---------------------------
# APP
# ---------------------------
app = Flask(__name__)
CORS(app)

# ---------------------------
# MODEL LOAD
# ---------------------------
model = load_model("audio_models.h5")

with open("labelencoder.pkl", "rb") as f:
    labelencoder = pickle.load(f)

# ---------------------------
# AUDIO SETTINGS
# ---------------------------
SAMPLE_RATE = 22050
DURATION = 3
N_MFCC = 40

# ---------------------------
# FAULT INFO
# ---------------------------
fault_info = {
    "Car": {"cause": "Timing chain wear detected", "severity": "High"},
    "Bus": {"cause": "Brake wear detected", "severity": "Critical"},
    "Trucks": {"cause": "Belt damage detected", "severity": "Medium"},
    "Motocycles": {"cause": "Bearing wear detected", "severity": "Medium"},
    "Train": {"cause": "Motor vibration issue", "severity": "High"},
    "airplane": {"cause": "Combustion issue", "severity": "Critical"},
    "helicopter": {"cause": "Rotor imbalance", "severity": "Critical"},
    "Bics": {"cause": "Chain wear", "severity": "Low"}
}

# ---------------------------
# FEATURE EXTRACTION
# ---------------------------
def extract_features(file_path):
    audio, sr = librosa.load(file_path, sr=SAMPLE_RATE, duration=DURATION)
    mfcc = librosa.feature.mfcc(y=audio, sr=sr, n_mfcc=N_MFCC)
    return np.mean(mfcc.T, axis=0).reshape(1, -1)

# ---------------------------
# API
# ---------------------------
@app.route("/predict-audio", methods=["POST"])
def predict_audio():

    if "file" not in request.files:
        return jsonify({"error": "No file uploaded"}), 400

    file = request.files["file"]

    with tempfile.NamedTemporaryFile(delete=False, suffix=".wav") as tmp:
        file.save(tmp.name)
        path = tmp.name

    try:
        features = extract_features(path)

        preds = model.predict(features, verbose=0)
        idx = np.argmax(preds)

        prediction = labelencoder.inverse_transform([idx])[0]
        confidence = float(np.max(preds) * 100)

        cause = fault_info.get(prediction, {}).get("cause", "Unknown")
        severity = fault_info.get(prediction, {}).get("severity", "Unknown")

        rag_result = get_repair_details(prediction, cause)

        return jsonify({
            "prediction": prediction,
            "confidence": confidence,
            "cause": cause,
            "severity": severity,
            "rag_result": rag_result
        })

    finally:
        if os.path.exists(path):
            os.remove(path)

# ---------------------------
# RUN
# ---------------------------
if __name__ == "__main__":
    app.run(host="0.0.0.0", port=10000)