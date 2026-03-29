from fastapi import FastAPI
from pydantic import BaseModel
import pickle
import os

app = FastAPI(title="E-commerce API")
db = [] # Memory DB

class Item(BaseModel):
    name: str
    price: float
    in_stock: bool = True
    sales: int
    views: int

# Endpoints:
@app.post("/items/")
def create_item(item: Item):
    db.append(item)
    return {"message": "Item créé", "item": item}


@app.get("/items/{item_id}")
def read_item(item_id: int):
    if item_id < len(db):
        return db[item_id]
    return {"error": "Item not found"}

# KPI:
@app.get("/items/")
def list_items():
    return {"items": db, "count": len(db)}

@app.get("/kpi/total_products")
def total_products():
    return {"total": len(db)}

@app.get("/kpi/total_value")
def total_value():
    total = sum(item.price for item in db)
    return {"total_value": total}

@app.get("/kpi/in_stock")
def in_stock_products():
    count = sum(1 for item in db if item.in_stock)
    return {"in_stock": count}

import pickle
with open("model.pkl", "rb") as f:
    model = pickle.load(f)
# Route pour faire une prédiction
@app.post("/predict")
def predict(item: Item):
    features = [[item.price, item.sales, item.views]]
    prediction = model.predict(features)[0]
    return {"prediction": prediction}