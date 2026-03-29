from fastapi import FastAPI
from pydantic import BaseModel
import random
from datetime import datetime

app = FastAPI(title="Cold-Chain Tracking Service")

class SensorData(BaseModel):
    container_id: str
    temperature_celsius: float

tracking_db = []

@app.get("/")
def health_check():
    return {"status": "healthy", "service": "Tracking Service"}

@app.post("/api/v1/track")
def record_temperature(data: SensorData):
    record = {
        "container_id": data.container_id,
        "temperature": data.temperature_celsius,
        "timestamp": datetime.now().isoformat(),
        "status": "WARNING" if data.temperature_celsius > -10.0 else "OK"
    }
    tracking_db.append(record)
    return {"message": "Data recorded successfully", "data": record}

@app.get("/api/v1/history/{container_id}")
def get_history(container_id: str):
    history = [record for record in tracking_db if record["container_id"] == container_id]
    return {"container_id": container_id, "history": history}