from dataclasses import dataclass

@dataclass
class Invoice:
    """
    Represents an invoice containing utility and maintenance charges.
    """
    address: str
    date: str
    electricity: float
    water: float
    trash: float
    elevator: float
    maintenance: float
    