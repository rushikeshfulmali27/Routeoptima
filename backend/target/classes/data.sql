-- Ports (Major Global Hubs)
INSERT INTO ports (name, latitude, longitude, capacity, country) VALUES
('Singapore Port', 1.264, 103.840, 500000, 'Singapore'),
('Shanghai Port', 31.230, 121.473, 800000, 'China'),
('Rotterdam Port', 51.922, 4.479, 300000, 'Netherlands'),
('Los Angeles Port', 33.728, -118.262, 450000, 'USA'),
('Hamburg Port', 53.548, 9.987, 250000, 'Germany'),
('Jebel Ali (Dubai)', 24.997, 55.060, 400000, 'UAE'),
('Mumbai Port', 18.940, 72.856, 350000, 'India'),
('Chennai Port', 13.085, 80.297, 300000, 'India'),
('Mundra Port', 22.753, 69.708, 450000, 'India'),
('New York / NJ', 40.669, -74.174, 400000, 'USA'),
('Tokyo Port', 35.619, 139.796, 320000, 'Japan'),
('Busan Port', 35.102, 129.074, 420000, 'South Korea'),
('Hong Kong', 22.345, 114.120, 480000, 'Hong Kong'),
('Sydney Port', -33.948, 151.189, 200000, 'Australia'),
('Cape Town', -33.906, 18.428, 150000, 'South Africa'),
('Santos Port', -23.968, -46.299, 280000, 'Brazil'),
('Felixstowe', 51.956, 1.313, 220000, 'UK');

-- Routes (Approximate Sea Paths)

-- Singapore <-> Shanghai
INSERT INTO routes (source_port_id, dest_port_id, distance_km, travel_time_hours, cost, path_coordinates) VALUES
(1, 2, 3800, 96, 5000, '[[1.264, 103.840], [3.5, 105.0], [10.0, 110.0], [20.0, 115.0], [25.0, 120.0], [31.230, 121.473]]');

-- Shanghai <-> LA (Pacific)
INSERT INTO routes (source_port_id, dest_port_id, distance_km, travel_time_hours, cost, path_coordinates) VALUES
(2, 4, 10500, 264, 12000, '[[31.230, 121.473], [34.0, 140.0], [40.0, 180.0], [38.0, -140.0], [33.728, -118.262]]');

-- Singapore <-> Mumbai
INSERT INTO routes (source_port_id, dest_port_id, distance_km, travel_time_hours, cost, path_coordinates) VALUES
(1, 7, 3900, 100, 4500, '[[1.264, 103.840], [5.9, 95.3], [6.0, 80.0], [10.0, 75.0], [18.940, 72.856]]');

-- Mumbai <-> Dubai
INSERT INTO routes (source_port_id, dest_port_id, distance_km, travel_time_hours, cost, path_coordinates) VALUES
(7, 6, 1950, 50, 2000, '[[18.940, 72.856], [20.0, 65.0], [24.0, 60.0], [24.997, 55.060]]');

-- Dubai <-> Rotterdam (Suez)
INSERT INTO routes (source_port_id, dest_port_id, distance_km, travel_time_hours, cost, path_coordinates) VALUES
(6, 3, 6400, 190, 8500, '[[24.997, 55.060], [12.6, 43.8], [13.4, 42.6], [28.0, 33.5], [31.5, 32.5], [32.0, 30.0], [35.0, 20.0], [36.0, -5.0], [45.0, -8.0], [50.0, -2.0], [51.922, 4.479]]');

-- Rotterdam <-> Hamburg
INSERT INTO routes (source_port_id, dest_port_id, distance_km, travel_time_hours, cost, path_coordinates) VALUES
(3, 5, 500, 12, 800, '[[51.922, 4.479], [53.0, 6.0], [54.0, 8.0], [53.548, 9.987]]');

-- Chennai <-> Singapore
INSERT INTO routes (source_port_id, dest_port_id, distance_km, travel_time_hours, cost, path_coordinates) VALUES
(8, 1, 2900, 72, 3000, '[[13.085, 80.297], [10.0, 90.0], [5.0, 98.0], [1.264, 103.840]]');

-- Mundra <-> Dubai
INSERT INTO routes (source_port_id, dest_port_id, distance_km, travel_time_hours, cost, path_coordinates) VALUES
(9, 6, 1200, 36, 1500, '[[22.753, 69.708], [23.5, 65.0], [24.997, 55.060]]');

-- Rotterdam <-> New York (Atlantic)
INSERT INTO routes (source_port_id, dest_port_id, distance_km, travel_time_hours, cost, path_coordinates) VALUES
(3, 10, 6000, 168, 7000, '[[51.922, 4.479], [50.0, -10.0], [45.0, -30.0], [42.0, -50.0], [40.669, -74.174]]');

-- New York <-> Felixstowe
INSERT INTO routes (source_port_id, dest_port_id, distance_km, travel_time_hours, cost, path_coordinates) VALUES
(10, 17, 5600, 150, 6800, '[[40.669, -74.174], [43.0, -50.0], [48.0, -20.0], [51.956, 1.313]]');

-- Felixstowe <-> Hamburg
INSERT INTO routes (source_port_id, dest_port_id, distance_km, travel_time_hours, cost, path_coordinates) VALUES
(17, 5, 600, 18, 900, '[[51.956, 1.313], [52.5, 4.0], [53.548, 9.987]]');

-- Shanghai <-> Tokyo
INSERT INTO routes (source_port_id, dest_port_id, distance_km, travel_time_hours, cost, path_coordinates) VALUES
(2, 11, 1800, 48, 2200, '[[31.230, 121.473], [32.0, 126.0], [33.0, 130.0], [35.619, 139.796]]');

-- Tokyo <-> LA (Pacific)
INSERT INTO routes (source_port_id, dest_port_id, distance_km, travel_time_hours, cost, path_coordinates) VALUES
(11, 4, 8800, 220, 10000, '[[35.619, 139.796], [36.0, 150.0], [40.0, 180.0], [38.0, -150.0], [33.728, -118.262]]');

-- Singapore <-> Hong Kong
INSERT INTO routes (source_port_id, dest_port_id, distance_km, travel_time_hours, cost, path_coordinates) VALUES
(1, 13, 2600, 60, 2800, '[[1.264, 103.840], [5.0, 106.0], [15.0, 112.0], [22.345, 114.120]]');

-- Hong Kong <-> Shanghai
INSERT INTO routes (source_port_id, dest_port_id, distance_km, travel_time_hours, cost, path_coordinates) VALUES
(13, 2, 1200, 30, 1400, '[[22.345, 114.120], [25.0, 119.0], [31.230, 121.473]]');

-- Sydney <-> Singapore
INSERT INTO routes (source_port_id, dest_port_id, distance_km, travel_time_hours, cost, path_coordinates) VALUES
(14, 1, 6300, 160, 7500, '[[1.264, 103.840], [-5.0, 105.0], [-15.0, 115.0], [-25.0, 130.0], [-33.948, 151.189]]');

-- Cape Town <-> Singapore (Indian Ocean)
INSERT INTO routes (source_port_id, dest_port_id, distance_km, travel_time_hours, cost, path_coordinates) VALUES
(15, 1, 9500, 240, 11000, '[[-33.906, 18.428], [-30.0, 40.0], [-15.0, 70.0], [-5.0, 90.0], [1.264, 103.840]]');

-- Santos <-> Cape Town (South Atlantic)
INSERT INTO routes (source_port_id, dest_port_id, distance_km, travel_time_hours, cost, path_coordinates) VALUES
(16, 15, 6000, 150, 6800, '[[-23.968, -46.299], [-28.0, -30.0], [-32.0, -10.0], [-33.906, 18.428]]');

-- New York <-> Santos
INSERT INTO routes (source_port_id, dest_port_id, distance_km, travel_time_hours, cost, path_coordinates) VALUES
(10, 16, 7500, 190, 8200, '[[40.669, -74.174], [25.0, -65.0], [10.0, -55.0], [0.0, -45.0], [-23.968, -46.299]]');
