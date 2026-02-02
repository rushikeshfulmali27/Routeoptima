import React, { useEffect, useState } from 'react';
import api from '../services/api';
import MapComponent from '../components/MapComponent';

const Dashboard = () => {
    const [stats, setStats] = useState({
        totalShipments: 0,
        totalPorts: 0,
        activeroutes: 0
    });
    const [ports, setPorts] = useState([]);

    useEffect(() => {
        fetchData();
    }, []);

    const fetchData = async () => {
        try {
            const portsRes = await api.get('/ports');
            const shipmentsRes = await api.get('/shipments');

            setPorts(portsRes.data);
            setStats({
                totalShipments: shipmentsRes.data.length,
                totalPorts: portsRes.data.length,
                activeroutes: 0 // Mock or calculate
            });
        } catch (error) {
            console.error("Error fetching dashboard data", error);
        }
    };

    return (
        <div>
            <h1>Logistics Dashboard</h1>

            <div style={{ display: 'grid', gridTemplateColumns: 'repeat(3, 1fr)', gap: '20px', marginBottom: '20px' }}>
                <div className="card">
                    <h3>Total Shipments</h3>
                    <p style={{ fontSize: '2em', fontWeight: 'bold' }}>{stats.totalShipments}</p>
                </div>
                <div className="card">
                    <h3>Active Ports</h3>
                    <p style={{ fontSize: '2em', fontWeight: 'bold' }}>{stats.totalPorts}</p>
                </div>
                <div className="card">
                    <h3>Active Deliveries</h3>
                    <p style={{ fontSize: '2em', fontWeight: 'bold' }}>12</p> {/* Mock data for demo */}
                </div>
            </div>

            <div className="card">
                <h3>Global Port Network</h3>
                <MapComponent ports={ports} />
            </div>
        </div>
    );
};

export default Dashboard;
