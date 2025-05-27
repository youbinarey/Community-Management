from dataclasses import dataclass

@dataclass
class Invoice:
    address: str
    date: str
    electricity: float
    water: float
    trash: float
    elevator: float
    maintenance: float
