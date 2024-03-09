import React from 'react';

const EditButton = ({ onClick, showForm }) => {
    return (
        <button onClick={onClick}>
            {showForm ? "Cancel Edit" : "Edit Parameters"}
        </button>
    );
};

export default EditButton;
