import os
from fastapi import FastAPI, UploadFile, File, Form
from fastapi.responses import JSONResponse
from email.message import EmailMessage
import smtplib

from dotenv import load_dotenv
load_dotenv()

# Vairables de entorno
EMAIL_ADDRESS = os.getenv("EMAIL")
EMAIL_PASSWORD = os.getenv("EMAIL_PASSWORD")

app = FastAPI()

# Endpoint para enviar un email con un PDF adjunto

@app.post("/send-invoice-email/")
async def send_invoice_email(
    recipient: str = Form(...),
    subject: str = Form(...),
    body: str = Form(...),
    file: UploadFile = File(...)
):
    try:
        # Lee pdf
        attachment_data = await file.read()

        # Factory email
        msg = EmailMessage()
        msg["From"] = EMAIL_ADDRESS
        msg["To"] = recipient
        msg["Subject"] = subject
        msg.set_content(body)
        msg.add_attachment(
            attachment_data, 
            maintype="application", 
            subtype="pdf", 
            filename=file.filename
        )

        # Enviar desde gmail
        with smtplib.SMTP_SSL("smtp.gmail.com", 465) as smtp:
            smtp.login(EMAIL_ADDRESS, EMAIL_PASSWORD)
            smtp.send_message(msg)

        return JSONResponse(content={"status": "ok"}, status_code=200)
    except Exception as e:
        return JSONResponse(content={"status": "error", "reason": str(e)}, status_code=500)
