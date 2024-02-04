import React, {useState} from 'react';

function TrolleyForm({onCreateTrolley}) {
    const [items, setItems] = useState([
        {type: 'BOOK', price: 5, quantity: 0},
        {type: 'CD', price: 10, quantity: 0},
        {type: 'DVD', price: 15, quantity: 0},
    ]);
    const [totalPrice, setTotalPrice] = useState(0);

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
        onCreateTrolley({items});
        setItems([
            {type: 'BOOK', price: 5, quantity: 0},
            {type: 'CD', price: 10, quantity: 0},
            {type: 'DVD', price: 15, quantity: 0},
        ]);
        setTotalPrice(0);
    };

    return (
        <form onSubmit={handleSubmit}>
            <style>{`
                table, th, td {
                    border: 2px solid black;
                    border-collapse: collapse;
                }
            `}</style>
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
                                <button type="button" onClick={() => handleQuantityChange(index, -1)}>-</button>
                                {item.quantity}
                                <button type="button" onClick={() => handleQuantityChange(index, 1)}>+</button>
                            </td>
                            <td>£{item.price * item.quantity}</td>
                        </tr>
                        {item.type === 'DVD' && item.quantity >= 2 && (
                            <tr>
                                <td colSpan="3" style={{textAlign: 'left'}}>
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
            <button type="submit">Checkout</button>
        </form>
    );
}

export default TrolleyForm;
