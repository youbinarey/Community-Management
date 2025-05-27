from flask import Flask, request, send_file
from models.invoice import Invoice
from services.pdf_service import generate_receipt_pdf

app = Flask(__name__)

@app.route('/generate-pdf', methods=['POST'])
def generate_pdf_endpoint():
    data = request.json
    invoice = Invoice(**data)
    pdf_file = generate_receipt_pdf(invoice)
    return send_file(
        pdf_file,
        as_attachment=True,
        download_name='recibo.pdf',
        mimetype='application/pdf'
    )

if __name__ == '__main__':
    app.run(debug=True)
