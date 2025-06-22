import io
from pathlib import Path
from weasyprint import CSS, HTML
from utils.helpers import format_spanish_date


BASE_DIR = Path(__file__).resolve().parent.parent  

def generate_receipt_pdf(invoice):
    """
    Generates a PDF receipt for a given invoice using an HTML template and CSS stylesheet.

    Args:
        invoice: An object containing invoice details such as address, date, electricity, water,
                 trash, elevator, and maintenance charges.

    Returns:
        io.BytesIO: A BytesIO object containing the generated PDF file.
    """
    fecha_formateada = format_spanish_date(invoice.date)
    template_path = BASE_DIR / "templates" / "template.html"
    css_path = BASE_DIR / "templates" / "css" / "template_styles.css"
    total = sum([
    invoice.electricity,
    invoice.water,
    invoice.trash,
    invoice.elevator,
    invoice.maintenance
])


    with open(template_path, encoding="utf-8") as f:
        html_content = f.read().format(
            address=invoice.address,
            date=fecha_formateada,
            electricity=invoice.electricity,
            water=invoice.water,
            trash=invoice.trash,
            elevator=invoice.elevator,
            maintenance=invoice.maintenance,
            total=f"{total:.2f}"  
        )
    pdf_file = io.BytesIO()
    HTML(string=html_content).write_pdf(pdf_file, stylesheets=[CSS(str(css_path))])  # <- Aplica CSS
    pdf_file.seek(0)
    return pdf_file


def generate_owner_receipt_pdf(invoice):
    """
    Generates a PDF receipt for an owner based on the provided invoice data.

    Args:
        invoice: An object containing invoice details. Expected to have the following attributes:
            - id: Unique identifier of the invoice.
            - date: Date of the invoice.
            - invoiceId: Invoice number or code.
            - ownerName: First name of the owner.
            - ownerSurname: Surname of the owner.
            - ownerBankAccount: Bank account number of the owner.
            - coefficient: Coefficient value associated with the invoice.
            - amount: Amount to be paid.
            - communityName: Name of the community.

    Returns:
        BytesIO: A BytesIO object containing the generated PDF file.
    """
    template_path = BASE_DIR / "templates" / "owner_template.html"
    css_path = BASE_DIR / "templates" / "css" / "owner_template_styles.css"

    with open(template_path, encoding="utf-8") as f:
        html_content = f.read().format(
            id=invoice.id,
            date=invoice.date,
            invoiceId=invoice.invoiceId,
            ownerName=invoice.ownerName,
            ownerSurname=invoice.ownerSurname,
            ownerBankAccount=invoice.ownerBankAccount,
            coefficient=f"{float(invoice.coefficient):.2f}",
            amount=f"{float(invoice.amount):.2f}",
            communityName=invoice.communityName
        )
    pdf_file = io.BytesIO()
    HTML(string=html_content).write_pdf(pdf_file, stylesheets=[CSS(str(css_path))])
    pdf_file.seek(0)
    return pdf_file
