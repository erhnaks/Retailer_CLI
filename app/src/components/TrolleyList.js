import React, { useState, useEffect } from 'react';

function TrolleyList({ trolleys }) {
    const [selectedOrders, setSelectedOrders] = useState([]);

    useEffect(() => {
    }, [trolleys]);

    const lastFiveOrders = trolleys.slice(-5).map(trolley => ({
        ...trolley,
        totalItems: trolley.items.reduce((sum, item) => sum + item.quantity, 0),
        total: trolley.items.reduce((sum, item) => sum + item.total, 0)
    }));

    const handleClick = (order) => {
        setSelectedOrders(prevOrders => {
            const isAlreadySelected = prevOrders.find(o => o.id === order.id);
            if (isAlreadySelected) {
                // Order is already selected, so replace it with the new order details
                return prevOrders.map(o => o.id === order.id ? order : o);
            } else {
                // Order is not selected, so add it to the array
                return [...prevOrders, order];
            }
        });
    };
    

    const totalSelectedItems = selectedOrders.reduce((sum, order) => sum + order.totalItems, 0);
    const totalSelectedTotal = selectedOrders.reduce((sum, order) => sum + order.total - order.totalDiscount, 0);
    const totalSelectedDiscount = selectedOrders.reduce((sum, order) => sum + (order.totalDiscount || 0), 0);

    return (
        <div>
            <div style={{ border: '2px solid #ccc', padding: '1rem', borderRadius: '20px' }}>
                <h3>History</h3>
                <ul>
                    {lastFiveOrders.map((order, index) => (
                        <li key={index} onClick={() => handleClick(order)}>
                            <span>items: {order.totalItems}, discount: £{order.totalDiscount}</span>
                        </li>
                    ))}
                </ul>
            </div>
            <div style={{ border: '2px solid #ccc', padding: '1rem', borderRadius: '20px', marginTop: '1rem' }}>
                <h3>Summary of selected trolleys:</h3>
                <p>
                    Total: £{totalSelectedTotal},
                    Total items: {totalSelectedItems},
                    Total Discount: £{totalSelectedDiscount}
                </p>
            </div>
        </div>
    );
}

export default TrolleyList;
