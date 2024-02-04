import React, { useState, useEffect } from 'react';
import '../index.css';

function TrolleyForm({ onCreateTrolley, products }) {
    const [items, setItems] = useState([]);
    const [totalPrice, setTotalPrice] = useState(0);

    useEffect(() => {
        const formattedItems = products.map(product => ({
            type: product.type,
            price: product.price,
            quantity: 0
        }));
        setItems(formattedItems);
    }, [products]);

    const handleQuantityChange = (index, increment) => {
        const newItems = [...items];
        newItems[index].quantity += increment;
        if (newItems[index].quantity < 0) {
            newItems[index].quantity = 0;
        }
        setItems(newItems);
        calculateTotalPrice(newItems);
    };

    const calculateTotalPrice = (items) => {
        let totalPrice = items.reduce((acc, item) => {
            let itemTotal = item.price * item.quantity;

            if (item.type === 'DVD' && item.quantity >= 2) {
                itemTotal -= item.price;
            }
            return acc + itemTotal;
        }, 0);
        setTotalPrice(totalPrice);
    };

    const handleSubmit = (event) => {
        event.preventDefault();
        onCreateTrolley({ items });
        setItems(products.map(product => ({
            type: product.type,
            price: product.price,
            quantity: 0
        })));
        setTotalPrice(0);
    };

    return (
        <form onSubmit={handleSubmit} className="trolley-form">
            <table>
                <thead>
                    <tr>
                        <th>Product</th>
                        <th>Price</th>
                        <th>Quantity</th>
                        <th>Total</th>
                    </tr>
                </thead>
                <tbody>
                    {items.map((item, index) => (
                        <>
                            <tr key={index}>
                                <td>{item.type}</td>
                                <td>£{item.price}</td>
                                <td>
                                    <button type="button" className="decrement-btn" 
                                    onClick={() => handleQuantityChange(index, -1)}>-</button>
                                    {item.quantity}
                                    <button type="button" onClick={() => handleQuantityChange(index, 1)}>+</button>
                                </td>
                                <td>£{item.price * item.quantity}</td>
                            </tr>
                            {item.type === 'DVD' && item.quantity >= 2 && (
                                <tr>
                                    <td colSpan="3" style={{ textAlign: 'left' }}>
                                        2 for 1
                                    </td>
                                    <td>-£15</td>
                                </tr>
                            )}
                        </>
                    ))}
                </tbody>
            </table>
            <div>
                <strong>Total: £{totalPrice}</strong>
            </div>
            <button type="submit" className="checkout-btn">Checkout</button>
        </form>
    );
}

export default TrolleyForm;
