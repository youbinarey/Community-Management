import io
from weasyprint import HTML
from utils.helpers import format_spanish_date

def generate_receipt_pdf(invoice):
    
    fecha_formateada = format_spanish_date(invoice.date)
    with open("templates/template.html", encoding="utf-8") as f:
        html_content = f.read().format(
            address=invoice.address,
            date=fecha_formateada,
            electricity=invoice.electricity,
            water=invoice.water,
            trash=invoice.trash,
            elevator=invoice.elevator,
            maintenance=invoice.maintenance,
        )
        
    pdf_file = io.BytesIO()
    HTML(string=html_content).write_pdf(pdf_file)
    pdf_file.seek(0)
    return pdf_file
