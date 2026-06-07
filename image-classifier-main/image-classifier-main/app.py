from flask import Flask, render_template, request
from ultralytics import YOLO
from werkzeug.utils import secure_filename
import os

# RAG Helper
from damage_rag_helper import get_damage_repair_details

app = Flask(__name__)

# --------------------------------------------------
# Configuration
# --------------------------------------------------

UPLOAD_FOLDER = "static/uploads"
ALLOWED_EXTENSIONS = {"jpg", "jpeg", "png"}

app.config["UPLOAD_FOLDER"] = UPLOAD_FOLDER

os.makedirs(UPLOAD_FOLDER, exist_ok=True)

# --------------------------------------------------
# Load YOLO Model
# --------------------------------------------------

MODEL_PATH = r"C:/Users/ACER/Downloads/image-classifier-main (1)/image-classifier-main/best.pt"

model = YOLO(MODEL_PATH)

# --------------------------------------------------
# Helper Function
# --------------------------------------------------

def allowed_file(filename):

    return (
        "." in filename
        and filename.rsplit(".", 1)[1].lower()
        in ALLOWED_EXTENSIONS
    )

# --------------------------------------------------
# Home Route
# --------------------------------------------------

@app.route("/", methods=["GET", "POST"])
def index():

    prediction_text = None
    confidence_text = None
    repair_details = None
    image_url = None

    if request.method == "POST":

        if "imagefile" not in request.files:

            return render_template(
                "index.html",
                prediction="No file uploaded"
            )

        imagefile = request.files["imagefile"]

        if imagefile.filename == "":

            return render_template(
                "index.html",
                prediction="No file selected"
            )

        if imagefile and allowed_file(imagefile.filename):

            filename = secure_filename(
                imagefile.filename
            )

            image_path = os.path.join(
                app.config["UPLOAD_FOLDER"],
                filename
            )

            imagefile.save(image_path)

            image_url = image_path

            try:

                # -----------------------------------
                # YOLO Prediction
                # -----------------------------------

                results = model(image_path)

                result = results[0]

                detected_labels = []

                confidences = []

                rag_outputs = []

                if len(result.boxes) > 0:

                    for box in result.boxes:

                        cls_id = int(box.cls[0])

                        confidence = float(
                            box.conf[0]
                        ) * 100

                        label = model.names[cls_id]

                        detected_labels.append(
                            label
                        )

                        confidences.append(
                            f"{confidence:.2f}%"
                        )

                        # -------------------------
                        # RAG Retrieval
                        # -------------------------

                        rag_result = (
                            get_damage_repair_details(
                                label
                            )
                        )

                        rag_outputs.append(
                            rag_result
                        )

                    prediction_text = ", ".join(
                        detected_labels
                    )

                    confidence_text = ", ".join(
                        confidences
                    )

                    repair_details = "\n\n".join(
                        rag_outputs
                    )

                else:

                    prediction_text = (
                        "No damage detected"
                    )

                    confidence_text = "N/A"

                    repair_details = (
                        "No repair information available."
                    )

            except Exception as e:

                prediction_text = (
                    "Prediction Error"
                )

                repair_details = str(e)

        else:

            prediction_text = (
                "Invalid file type."
            )

    return render_template(
    "index.html",
    prediction=prediction_text,
    confidence=confidence_text,
    repair=repair_details,
    image_url="/" + image_url.replace("\\", "/")
    if image_url else None
)

# --------------------------------------------------
# Run Application
# --------------------------------------------------

if __name__ == "__main__":

    app.run(
        host="0.0.0.0",
        port=4000,
        debug=True
    )