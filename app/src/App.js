import React, {useEffect, useState} from 'react';
import TrolleyForm from './components/TrolleyForm';
import TrolleyList from './components/TrolleyList';

function App() {
    const [products, setProducts] = useState([]);
    const [trolleys, setTrolleys] = useState([]);

    useEffect(() => {
        fetchProducts();
        fetchTrolleys();
    }, []);

    const fetchProducts = async () => {
        const response = await fetch('/api/products');
        const data = await response.json();
        setProducts(data);
    };

    const fetchTrolleys = async () => {
        const response = await fetch('/api/trolleys');
        const data = await response.json();
        setTrolleys(data);
    };

    const handleCreateTrolley = async (trolleyData) => {
        try {
            const response = await fetch('/api/trolley', {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/json'
                },
                body: JSON.stringify(trolleyData)
            });
            if (response.ok) {
               fetchTrolleys();
            } else {
                console.error('Failed to create trolley');
            }
        } catch (error) {
            console.error('Error creating trolley:', error);
        }
    };

    return (
        <div className="App" style={{
            display: 'flex',
            flexDirection: 'row',
            alignItems: 'center',
            justifyContent: 'center',
            minHeight: '100vh',
            padding: '1rem'
        }}>
            <div style={{width: '35%', marginRight: 'auto', marginLeft: '0'}}>

                <TrolleyList trolleys={trolleys}/>
            </div>
            <div style={{width: '45%', marginLeft: 'auto', marginRight: '0'}}>
                <TrolleyForm onCreateTrolley={handleCreateTrolley}/>
            </div>
        </div>
    );
}

export default App;
