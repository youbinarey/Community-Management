from datetime import datetime

def format_spanish_date(fecha_str):
    try:
        fecha_obj = datetime.strptime(fecha_str, "%Y-%m-%d")
        return fecha_obj.strftime("%d-%m-%Y")
    except Exception:
        return fecha_str
