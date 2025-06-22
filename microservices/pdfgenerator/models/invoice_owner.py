from dataclasses import dataclass
from typing import Optional, List

@dataclass
class OwnerInvoice:
    """
    Represents an invoice associated with a property owner.
    """
    id: int
    date: str
    amount: float
    invoiceId: int
    ownerName: str
    ownerSurname: str
    ownerBankAccount: str
    coefficient: float
    communityName: str
    properties: Optional[list] = None  #TODO
    email: Optional[str] = None
