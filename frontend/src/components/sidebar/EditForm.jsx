import React, { useState, useEffect } from 'react';
import { Button, Dialog, DialogActions, DialogContent, DialogTitle, FormControl, InputLabel, MenuItem, Select, Checkbox, FormControlLabel } from '@mui/material';

const EditForm = ({ onClose }) => {
    const [selectedRoom, setSelectedRoom] = useState('');
    const [selectedInhabitant, setSelectedInhabitant] = useState('');
    const [selectedWindow, setSelectedWindow] = useState('');
    const [isWindowBlocked, setIsWindowBlocked] = useState(false);

    const [inhabitants, setInhabitants] = useState([]);
    const [rooms, setRooms] = useState([]);
    const [windows, setWindows] = useState([]);

    // Fetch data from backend on mount
    useEffect(() => {  
        fetch('http://localhost:8080/getData') 
            .then(response => response.json())
            .then(data => {
                setInhabitants(data.inhabitants);
                setRooms(data.rooms);
                setWindows(data.windows);
            })
            .then(console.log('Edit Form Data fetched successfully!'))
            .catch(error => console.error('Error fetching data:', error));
    }, []);


    // Post data on submission
    const handleSubmit = (e) => {
        e.preventDefault();

        const formData = {
            selectedRoom: selectedRoom,
            selectedInhabitant: selectedInhabitant,
            selectedWindow: selectedWindow,
            isWindowBlocked: isWindowBlocked
        };

        fetch('http://localhost:8080/submitForm', { 
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(formData)
        })
            .then(response => {
                if (response.ok) {
                    console.log('Form submitted successfully!');
                    onClose();
                } else {
                    console.error('Error submitting form:', response.statusText);
                }
            })
            .catch(error => {
                console.error('Error submitting form:', error);
            });
    };

    return (
        <Dialog open={true} onClose={onClose}>
            <DialogTitle >Edit Context</DialogTitle>
            <DialogContent >
                <form onSubmit={handleSubmit}>
                    <FormControl fullWidth sx={{ marginBottom: '1rem' }}>
                        <InputLabel>Select Room</InputLabel>
                        <Select
                            value={selectedRoom}
                            onChange={(e) => setSelectedRoom(e.target.value)}
                            label="Select Room" // Add label to prevent floating
                        >
                            <MenuItem value="">
                                <em>Select Room</em>
                            </MenuItem>
                            {rooms.map((room, index) => (
                                <MenuItem key={index} value={room}>{room}</MenuItem>
                            ))}
                        </Select>
                    </FormControl>

                    <FormControl fullWidth sx={{ marginBottom: '1rem' }}>
                        <InputLabel>Select Inhabitant</InputLabel>
                        <Select
                            value={selectedInhabitant}
                            onChange={(e) => setSelectedInhabitant(e.target.value)}
                            label="Select Inhabitant" // Add label to prevent floating
                        >
                            <MenuItem value="">
                                <em>Select Inhabitant</em>
                            </MenuItem>
                            {inhabitants.map((inhabitant, index) => (
                                <MenuItem key={index} value={inhabitant}>{inhabitant}</MenuItem>
                            ))}
                        </Select>
                    </FormControl>

                    <FormControl fullWidth sx={{ marginBottom: '1rem' }}>
                        <InputLabel>Select Window</InputLabel>
                        <Select
                            value={selectedWindow}
                            onChange={(e) => setSelectedWindow(e.target.value)}
                            label="Select Window" // Add label to prevent floating
                        >
                            <MenuItem value="">
                                <em>Select Window</em>
                            </MenuItem>
                            {windows.map((window, index) => (
                                <MenuItem key={index} value={window}>{window}</MenuItem>
                            ))}
                        </Select>
                    </FormControl>

                    <FormControlLabel
                        control={
                            <Checkbox
                                checked={isWindowBlocked}
                                onChange={(e) => setIsWindowBlocked(e.target.checked)}
                            />
                        }
                        label="Block Window"
                    />

                    <DialogActions>
                        <Button onClick={onClose} color="primary">
                            Cancel
                        </Button>
                        <Button type="submit" color="primary">
                            Submit
                        </Button>
                    </DialogActions>
                </form>
            </DialogContent>
        </Dialog>
    );
};

export default EditForm;
