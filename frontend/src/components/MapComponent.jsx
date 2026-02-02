import React from 'react';
import { MapContainer, TileLayer, Marker, Popup, Polyline } from 'react-leaflet';
import 'leaflet/dist/leaflet.css';
import L from 'leaflet';
import icon from 'leaflet/dist/images/marker-icon.png';
import iconShadow from 'leaflet/dist/images/marker-shadow.png';

let DefaultIcon = L.icon({
    iconUrl: icon,
    shadowUrl: iconShadow,
    iconSize: [25, 41],
    iconAnchor: [12, 41]
});

L.Marker.prototype.options.icon = DefaultIcon;

const MapComponent = ({ ports = [], routePath = [] }) => {
    // routePath is now expected to be an array of arrays of coordinates: [[[lat, lng], ...], [[lat, lng], ...]]
    // or a flat array of coordinates if it's a simple line.

    // Check if routePath elements are arrays (segments) or points
    const normalizePath = () => {
        if (!routePath || routePath.length === 0) return [];
        // If it's a list of segments (from DTO pathCoordinates), keep structure or flatten?
        // Polyline supports multi-polyline: [[lat,lng],[lat,lng]] is one line.
        // [[[lat,lng],[lat,lng]], [[lat,lng]...]] is multiple disconnected lines.
        // Dijkstra path is continuous, so we can flatten or keep as segments. keeping as segments is safer if there are gaps.
        return routePath;
    };

    return (
        <MapContainer center={[20, 78]} zoom={4} style={{ height: '500px', width: '100%', borderRadius: '10px' }}>
            <TileLayer
                attribution='&copy; <a href="https://www.openstreetmap.org/copyright">OpenStreetMap</a> contributors'
                url="https://{s}.tile.openstreetmap.org/{z}/{x}/{y}.png"
            />

            {ports.map((port) => (
                <Marker key={port.id} position={[port.latitude, port.longitude]}>
                    <Popup>
                        <strong>{port.name}</strong><br />
                        Capacity: {port.capacity}<br />
                        Country: {port.country}
                    </Popup>
                </Marker>
            ))}

            {routePath.length > 0 && (
                <Polyline positions={routePath} color="#ef4444" weight={3} dashArray="5, 5" />
            )}
        </MapContainer>
    );
};

export default MapComponent;
