import React from 'react';

function ProductList({ products }) {
    return (
        <ul>
            {products.map((product, index) => (
                <li key={index}>{product.name}</li>
            ))}
        </ul>
    );
}

export default ProductList;
