import React, { useState, useEffect } from 'react';
import api from '../services/api';
import MapComponent from '../components/MapComponent';

const RoutePlanner = () => {
    const [ports, setPorts] = useState([]);
    const [formData, setFormData] = useState({
        sourceId: '',
        destId: '',
        weight: '1000',
        volume: '10',
        productType: 'Standard'
    });
    const [routePath, setRoutePath] = useState([]);
    const [pathText, setPathText] = useState('');
    const [totals, setTotals] = useState({ distance: 0, cost: 0, time: 0 });

    useEffect(() => {
        const fetchPorts = async () => {
            const res = await api.get('/ports');
            setPorts(res.data);
        };
        fetchPorts();
    }, []);

    const handleChange = (e) => {
        setFormData({ ...formData, [e.target.name]: e.target.value });
    };

    const handleCalculate = async () => {
        if (!formData.sourceId || !formData.destId) {
            alert('Select both ports');
            return;
        }
        try {
            const res = await api.get(`/optimize/shortest-path`, {
                params: {
                    sourceId: formData.sourceId,
                    destId: formData.destId,
                    weight: formData.weight,
                    productType: formData.productType
                }
            });
            const routes = res.data;

            if (routes.length === 0) {
                alert('No path found');
                setRoutePath([]);
                setTotals({ distance: 0, cost: 0, time: 0 });
                setPathText('');
                return;
            }

            const segments = [];
            let totalDist = 0;
            let totalCost = 0;
            let totalTime = 0;

            routes.forEach((r) => {
                totalDist += r.distanceKm;
                totalCost += r.cost;
                totalTime += r.travelTimeHours || 0;

                if (r.pathCoordinates) {
                    try {
                        const coords = JSON.parse(r.pathCoordinates);
                        segments.push(coords);
                    } catch (e) {
                        const p1 = ports.find(p => p.id === r.sourcePortId);
                        const p2 = ports.find(p => p.id === r.destPortId);
                        if (p1 && p2) segments.push([[p1.latitude, p1.longitude], [p2.latitude, p2.longitude]]);
                    }
                } else {
                    const p1 = ports.find(p => p.id === r.sourcePortId);
                    const p2 = ports.find(p => p.id === r.destPortId);
                    if (p1 && p2) segments.push([[p1.latitude, p1.longitude], [p2.latitude, p2.longitude]]);
                }
            });

            setTotals({ distance: totalDist, cost: totalCost, time: totalTime });
            setPathText(`Optimal path found with ${routes.length} segments.`);
            setRoutePath(segments);
        } catch (err) {
            console.error(err);
            alert('Failed to calculate route');
        }
    };

    return (
        <div>
            <h1>Advanced Route Planner</h1>

            <div className="card">
                <div style={{ display: 'grid', gridTemplateColumns: 'repeat(5, 1fr)', gap: '15px' }}>

                    <div style={{ gridColumn: 'span 1' }}>
                        <label>Source Port</label>
                        <select name="sourceId" value={formData.sourceId} onChange={handleChange}>
                            <option value="">Select Source</option>
                            {ports.map(p => <option key={p.id} value={p.id}>{p.name}</option>)}
                        </select>
                    </div>

                    <div style={{ gridColumn: 'span 1' }}>
                        <label>Destination</label>
                        <select name="destId" value={formData.destId} onChange={handleChange}>
                            <option value="">Select Dest</option>
                            {ports.map(p => <option key={p.id} value={p.id}>{p.name}</option>)}
                        </select>
                    </div>

                    <div>
                        <label>Weight (kg)</label>
                        <input type="number" name="weight" value={formData.weight} onChange={handleChange} />
                    </div>

                    <div>
                        <label>Product Type</label>
                        <select name="productType" value={formData.productType} onChange={handleChange}>
                            <option value="Standard">Standard</option>
                            <option value="Fragile">Fragile (1.5x)</option>
                            <option value="Hazardous">Hazardous (2.5x)</option>
                            <option value="Perishable">Perishable (1.8x)</option>
                        </select>
                    </div>

                    <div style={{ display: 'flex', alignItems: 'flex-end' }}>
                        <button className="btn" onClick={handleCalculate} style={{ width: '100%', marginBottom: '10px' }}>
                            Calculate Cost
                        </button>
                    </div>
                </div>
            </div>

            {pathText && (
                <div className="card">
                    <h3>Trip Summary</h3>
                    <div style={{ display: 'grid', gridTemplateColumns: '1fr 1fr 1fr', gap: '20px', marginTop: '10px' }}>
                        <div style={{ background: '#0f172a', padding: '15px', borderRadius: '8px' }}>
                            <span style={{ color: '#94a3b8', fontSize: '0.9em' }}>Total Distance</span>
                            <div style={{ fontSize: '1.8em', fontWeight: 'bold' }}>{totals.distance.toLocaleString()} km</div>
                        </div>
                        <div style={{ background: '#0f172a', padding: '15px', borderRadius: '8px' }}>
                            <span style={{ color: '#94a3b8', fontSize: '0.9em' }}>Est. Time</span>
                            <div style={{ fontSize: '1.8em', fontWeight: 'bold' }}>{(totals.time / 24).toFixed(1)} days</div>
                        </div>
                        <div style={{ background: '#0f172a', padding: '15px', borderRadius: '8px', border: '1px solid #10b981' }}>
                            <span style={{ color: '#94a3b8', fontSize: '0.9em' }}>Total Cost</span>
                            <div style={{ fontSize: '1.8em', fontWeight: 'bold', color: '#10b981' }}>${totals.cost.toLocaleString(undefined, { maximumFractionDigits: 2 })}</div>
                        </div>
                    </div>
                </div>
            )}

            <div className="card">
                <MapComponent ports={ports} routePath={routePath} />
            </div>
        </div>
    );
};

export default RoutePlanner;
