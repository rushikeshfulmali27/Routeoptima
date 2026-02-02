import React, { useState, useEffect } from 'react';
import api from '../services/api';

const Ports = () => {
    const [ports, setPorts] = useState([]);
    const [formData, setFormData] = useState({
        name: '',
        latitude: '',
        longitude: '',
        capacity: '',
        country: ''
    });

    useEffect(() => {
        fetchPorts();
    }, []);

    const fetchPorts = async () => {
        try {
            const res = await api.get('/ports');
            setPorts(res.data);
        } catch (err) {
            console.error(err);
        }
    };

    const handleChange = (e) => {
        setFormData({ ...formData, [e.target.name]: e.target.value });
    };

    const handleSubmit = async (e) => {
        e.preventDefault();
        try {
            await api.post('/ports', formData);
            fetchPorts();
            setFormData({ name: '', latitude: '', longitude: '', capacity: '', country: '' });
        } catch (err) {
            console.error(err);
            alert('Error creating port');
        }
    };

    const handleDelete = async (id) => {
        if (!window.confirm('Are you sure?')) return;
        try {
            await api.delete(`/ports/${id}`);
            fetchPorts();
        } catch (err) {
            console.error(err);
        }
    };

    return (
        <div>
            <h1>Port Manager</h1>

            <div className="card">
                <h3>Add New Port</h3>
                <form onSubmit={handleSubmit} style={{ display: 'grid', gridTemplateColumns: '1fr 1fr', gap: '10px' }}>
                    <input name="name" placeholder="Port Name" value={formData.name} onChange={handleChange} required />
                    <input name="country" placeholder="Country" value={formData.country} onChange={handleChange} required />
                    <input name="latitude" type="number" step="any" placeholder="Latitude" value={formData.latitude} onChange={handleChange} required />
                    <input name="longitude" type="number" step="any" placeholder="Longitude" value={formData.longitude} onChange={handleChange} required />
                    <input name="capacity" type="number" placeholder="Capacity (TEU)" value={formData.capacity} onChange={handleChange} />
                    <button type="submit" className="btn" style={{ gridColumn: 'span 2' }}>Add Port</button>
                </form>
            </div>

            <div className="card">
                <h3>Current Ports</h3>
                <table style={{ width: '100%', borderCollapse: 'collapse', color: 'white' }}>
                    <thead>
                        <tr style={{ textAlign: 'left', borderBottom: '1px solid #475569' }}>
                            <th style={{ padding: '10px' }}>Name</th>
                            <th>Country</th>
                            <th>Location (Lat, Lng)</th>
                            <th>Capacity</th>
                            <th>Actions</th>
                        </tr>
                    </thead>
                    <tbody>
                        {ports.map(port => (
                            <tr key={port.id} style={{ borderBottom: '1px solid #334155' }}>
                                <td style={{ padding: '10px' }}>{port.name}</td>
                                <td>{port.country}</td>
                                <td>{port.latitude}, {port.longitude}</td>
                                <td>{port.capacity}</td>
                                <td>
                                    <button onClick={() => handleDelete(port.id)} style={{ background: 'red', color: 'white', border: 'none', padding: '5px 10px', borderRadius: '4px', cursor: 'pointer' }}>
                                        Delete
                                    </button>
                                </td>
                            </tr>
                        ))}
                    </tbody>
                </table>
            </div>
        </div>
    );
};

export default Ports;
