const express = require('express');
const app = express();
const port = 3000;

app.use(express.json());

let inventory = [
    { itemId: "VACCINE-001", name: "Pfizer Vax", quantity: 5000, requiredTemp: -70 },
    { itemId: "FOOD-002", name: "Frozen Seafood", quantity: 200, requiredTemp: -18 }
];

app.get('/', (req, res) => {
    res.json({ status: "healthy", service: "Inventory Service" });
});

app.get('/api/v1/inventory', (req, res) => {
    res.json(inventory);
});

app.post('/api/v1/inventory', (req, res) => {
    const newItem = req.body;
    inventory.push(newItem);
    res.status(201).json({ message: "Item added successfully", item: newItem });
});

app.listen(port, () => {
    console.log(`Inventory Service listening on port ${port}`);
});