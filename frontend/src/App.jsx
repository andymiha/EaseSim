import React, { useState } from 'react';
import EditButton from './components/EditButton';
import EditForm from './components/EditForm';

const App = () => {
    const [showForm, setShowForm] = useState(false);

    const handleEditClick = () => {
        setShowForm(!showForm); // Toggle showForm state
    };

    return (
        <div>
            <EditButton onClick={handleEditClick} showForm={showForm} />
            {showForm && <EditForm onClose={handleEditClick} />} {/* Pass handleEditClick as onClose */}
        </div>
    );
};

export default App;
