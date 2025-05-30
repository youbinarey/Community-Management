from flask import Flask, request, send_file
from models.invoice import Invoice
from models.invoice_owner import OwnerInvoice
from services.pdf_service import generate_owner_receipt_pdf
from services.pdf_service import generate_receipt_pdf

app = Flask(__name__)

@app.route('/generate-community-receipt', methods=['POST'])
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
    
@app.route('/generate-owner-receipt', methods=['POST'])
def generate_owner_receipt():
    data = request.json
    invoice = OwnerInvoice(**data)
    pdf_file = generate_owner_receipt_pdf(invoice)
    return send_file(
        pdf_file,
        as_attachment=True,
        download_name='recibo_propietario.pdf',
        mimetype='application/pdf'
    )

if __name__ == '__main__':
    app.run(debug=True)
