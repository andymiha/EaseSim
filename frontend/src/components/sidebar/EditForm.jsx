import React, { useState, useEffect } from 'react';
import { Button, Dialog, DialogActions, DialogContent, DialogTitle, FormControl, InputLabel, MenuItem, Select, Switch, FormControlLabel, Grid } from '@mui/material';

const EditForm = ({ onClose }) => {
    const [rooms, setRooms] = useState([]);
    const [selectedRoom, setSelectedRoom] = useState('');
    const [selectedInhabitant, setSelectedInhabitant] = useState('');
    const [selectedWindow, setSelectedWindow] = useState('');
    const [isWindowBlocked, setIsWindowBlocked] = useState(false);
    const [isButtonModified, setIsButtonModified] = useState(false);
    const [initialWindowBlocked, setInitialWindowBlocked] = useState(false); // New state variable to store initial window blocked state

    useEffect(() => {
        const fetchData = async () => {
            try {
                const response = await fetch('http://localhost:8080/getData');
                if (!response.ok) {
                    throw new Error('Failed to fetch data');
                }
                const data = await response.json();
                console.log('Rooms:', data.rooms);
                setRooms(data.rooms);
            } catch (error) {
                console.error('Error fetching data:', error);
            }
        };

        fetchData();
    }, []);

    const inhabitants = ["John", "Mary", "Bob", "Alice"]; // Hardcoded inhabitants

    const handleSubmit = async (e) => {
        e.preventDefault();

        const formData = {
            selectedRoom: selectedRoom,
            selectedInhabitant: selectedInhabitant,
            selectedWindow: selectedWindow,
            isWindowBlocked: isWindowBlocked
        };

        try {
            const response = await fetch('http://localhost:8080/submitForm', {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/json'
                },
                body: JSON.stringify(formData)
            });

            if (!response.ok) {
                throw new Error('Failed to submit form');
            }

            console.log('Form submitted successfully!');
            onClose();
        } catch (error) {
            console.error('Error submitting form:', error);
        }
    };

    useEffect(() => {
        if (selectedWindow !== '') {
            console.log('Entered isBlocked check')
            
            const room = rooms.find(room => room.name === selectedRoom);
            const window = room?.windows.find(window => window.id === selectedWindow);
            
            console.log('Selected window:', window);
            console.log('Window block state: ', window.blockedState);
            
            setInitialWindowBlocked(window?.blockedState || false); // Store the initial window blocked state
            setIsWindowBlocked(window?.blockedState || false);
            setIsButtonModified(false);
        }
    }, [selectedWindow, rooms]);

    const handleSwitchChange = (e) => {
        setIsWindowBlocked(e.target.checked);
        setIsButtonModified(true);
    };
    
    return (
        <Dialog open={true} onClose={onClose} fullWidth={true} maxWidth="sm">
            <DialogTitle>Edit Context</DialogTitle>
            <DialogContent> 
                <form onSubmit={handleSubmit}>
                    <InputLabel sx={{ marginBottom: '1rem' }}>Select Room</InputLabel>
                    <FormControl fullWidth sx={{ marginBottom: '1rem' }}>
                        <InputLabel>Select Room</InputLabel>
                        <Select
                            value={selectedRoom}
                            onChange={(e) => setSelectedRoom(e.target.value)}
                            label="Select Room"
                        >
                            <MenuItem value="">
                                <em>Select Room</em>
                            </MenuItem>
                            {rooms.map((room) => (
                                <MenuItem key={room.id} value={room.name}>{room.name}</MenuItem>
                            ))}
                        </Select>
                    </FormControl>

                    <InputLabel sx={{ marginBottom: '1rem', marginTop: '1rem'  }}>Select Inhabitant</InputLabel>
                    <FormControl fullWidth sx={{ marginBottom: '1rem' }}>
                        <InputLabel id="inhabitant-label">Inhabitant</InputLabel>
                        <Select
                            value={selectedInhabitant}
                            onChange={(e) => setSelectedInhabitant(e.target.value)}
                            labelId="inhabitant-label"
                            label="Inhabitant"
                            disabled={!selectedRoom || selectedWindow !== ''}
                        >
                            <MenuItem value="">
                                <em>Select Inhabitant</em>
                            </MenuItem>
                            {inhabitants.map((inhabitant, index) => (
                                <MenuItem key={index} value={inhabitant}>{inhabitant}</MenuItem>
                            ))}
                        </Select>
                    </FormControl>

                    <InputLabel sx={{ marginBottom: '1rem', marginTop: '1rem'  }}>Block Window</InputLabel>
                    <Grid container spacing={2} alignItems="center">
                        <Grid item xs={6}>
                            <FormControl fullWidth>
                                <InputLabel>Select Window</InputLabel>
                                <Select
                                    value={selectedWindow}
                                    onChange={(e) => setSelectedWindow(e.target.value)}
                                    label="Select Window"
                                    disabled={!selectedRoom || selectedInhabitant !== ''}
                                >
                                    <MenuItem value="">
                                        <em>Select Window</em>
                                    </MenuItem>
                                    {rooms.find((room) => room.name === selectedRoom)?.windows.map((window) => (
                                        <MenuItem key={window.id} value={window.id}>Window {window.id}</MenuItem>
                                    ))}
                                </Select>
                            </FormControl>
                        </Grid>
                        <Grid item xs={6}>
                            <FormControlLabel
                                control={<Switch checked={isWindowBlocked} onChange={handleSwitchChange} />}
                                label={isWindowBlocked ? "Blocked" : "Unblocked"}
                                disabled={!selectedWindow}
                            />
                        </Grid>
                    </Grid>

                    <DialogActions>
                        <Button onClick={onClose} color="primary">
                            Cancel
                        </Button>
                        <Button
                            type="submit"
                            color="primary"
                            disabled={
                                !selectedRoom || // Disable if no room is selected
                                !(selectedInhabitant || selectedWindow) || // Disable if no inhabitant or window is selected
                                (!isButtonModified || isWindowBlocked === initialWindowBlocked) // Disable if the Switch is in its initial state
                            }
                        >
                            Submit
                        </Button>
                    </DialogActions>
                </form>
            </DialogContent>
        </Dialog>
    );
};

export default EditForm;
