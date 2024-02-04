import React, { useState, useEffect } from 'react';
import '../index.css';

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
            return prevOrders.map(o => o.id === order.id ? order : o);
        } else {
            return [...prevOrders, order];
        }
    });
};


    const totalSelectedItems = selectedOrders.reduce((sum, order) => sum + order.totalItems, 0);
    const totalSelectedTotal = selectedOrders.reduce((sum, order) => sum + order.total - order.totalDiscount, 0);
    const totalSelectedDiscount = selectedOrders.reduce((sum, order) => sum + (order.totalDiscount || 0), 0);

    return (
        <div>
            <div className="history">
                <h3>History</h3>
                <div className="centered-list">
                    <ul>
                        {lastFiveOrders.map((order, index) => (
                            <li key={index} onClick={() => handleClick(order)}>
                                <span>items: {order.totalItems}, discount: £{order.totalDiscount}</span>
                            </li>
                        ))}
                    </ul>
                </div>
            </div>
            <div className="summary">
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
