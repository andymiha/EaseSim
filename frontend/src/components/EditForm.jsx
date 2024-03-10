import React, { useState } from 'react';

const EditForm = ({ onClose }) => {
    const [selectedRoom, setSelectedRoom] = useState('');
    const [selectedInhabitant, setSelectedInhabitant] = useState('');
    const [selectedWindow, setSelectedWindow] = useState('');
    const [isWindowBlocked, setIsWindowBlocked] = useState(false);

    const inhabitants = ['Andy', 'Mehdi'];
    const rooms = ['Living Room', 'Kitchen', 'Bedroom', 'Bathroom', 'Study'];
    const windows = ['Window 1', 'Window 2', 'Window 3']; // List of available windows

    const handleSubmit = (e) => {
        e.preventDefault();

        const formData = {
            selectedRoom: selectedRoom,
            selectedInhabitant: selectedInhabitant,
            selectedWindow: selectedWindow,
            isWindowBlocked: isWindowBlocked
        };

        fetch('/submitForm', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(formData)
        })
        .then(response => {
            if (response.ok) {
                console.log('Form submitted successfully!');
                onClose(); // Close the form after successful submission
            } else {
                console.error('Error submitting form:', response.statusText);
                // Handle error response here
            }
        })
        .catch(error => {
            console.error('Error submitting form:', error);
            // Handle network errors here
        });
    };

    const handleCancel = () => {
        onClose(); // Close the form without submission
    };

    return (
        <form onSubmit={handleSubmit}>
            <h3>Select Room</h3>
            <div>
                <label>
                    <select value={selectedRoom} onChange={(e) => setSelectedRoom(e.target.value)}>
                        <option value="">Select Room</option>
                        {rooms.map((room, index) => (
                            <option key={index} value={room}>{room}</option>
                        ))}
                    </select>
                </label>
            </div>

            <h3>Place Inhabitants</h3>
            <div>
                <label>
                    <select value={selectedInhabitant} onChange={(e) => setSelectedInhabitant(e.target.value)}>
                        <option value="">Select Inhabitant</option>
                        {inhabitants.map((inhabitant, index) => (
                            <option key={index} value={inhabitant}>{inhabitant}</option>
                        ))}
                    </select>
                </label>
            </div>

            <h3>Block Windows</h3>
            <div>
                <label>
                    Select Window:
                    <select value={selectedWindow} onChange={(e) => setSelectedWindow(e.target.value)}>
                        <option value="">Select Window</option>
                        {windows.map((window, index) => (
                            <option key={index} value={window}>{window}</option>
                        ))}
                    </select>
                </label>
            </div>
            <div>
                <label>
                    Block Window:
                    <input
                        type="checkbox"
                        checked={isWindowBlocked}
                        onChange={(e) => setIsWindowBlocked(e.target.checked)}
                    />
                </label>
            </div>

            <button type="submit">Submit</button>
            <button type="button" onClick={handleCancel}>Cancel</button>
        </form>
    );
};

export default EditForm;
