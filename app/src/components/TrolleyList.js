import React, { useState, useEffect } from 'react';
import { v4 as uuidv4 } from 'uuid'; 
import '../index.css';

function TrolleyList({ trolleys }) {
    const [selectedOrders, setSelectedOrders] = useState([]);

    useEffect(() => {
    }, [trolleys]);

    const lastFiveOrders = trolleys.slice(-5).map(trolley => ({
        uuid: uuidv4(),
        ...trolley,
        totalItems: trolley.items.reduce((sum, item) => sum + item.quantity, 0),
        total: trolley.items.reduce((sum, item) => sum + item.total, 0)
    }));
    

    const handleClick = (order) => {
        setSelectedOrders(prevOrders => {
            const isAlreadySelected = prevOrders.find(o => o.uuid === order.uuid);
            console.log('Is already selected:', isAlreadySelected);
            if (isAlreadySelected) {
                return prevOrders.filter(o => o.uuid !== order.uuid);
            } else {
                return [...prevOrders, order];
            }
        });
    };
    

    const totalSelectedItems = selectedOrders.reduce((sum, order) => sum + order.totalItems, 0);
    const totalSelectedTotal = selectedOrders.reduce((sum, order) => sum + order.total - order.totalDiscount, 0);
    const totalSelectedDiscount = selectedOrders.reduce((sum, order) => sum + (order.totalDiscount || 0), 0);

    const resetSummary = () => {
        setSelectedOrders([]);
    };

    return (
        <div>
            <div className="history">
                <h3>History</h3>
                <div className="centered-list">
                    <ul>
                    {lastFiveOrders.map((order, index) => (
                        <li
                            key={index}
                            onClick={() => handleClick(order)}
                            className={selectedOrders.some(o => o.uuid === order.uuid) ? 'highlighted-order' : ''}
                        >
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
                <button className="reset-button"  onClick={resetSummary}>Reset Summary</button>
            </div>
        </div>
    );
}

export default TrolleyList;
