-- Insert demo users
INSERT INTO users (name, email, password, created_at, updated_at) VALUES
('Demo User', 'demo@klumusic.com', '$2a$10$7xvq.CqZlJgd1tYy1JlYt.QdVyj1P0xZ1rKzL.qGcvhtCzrfJ.j6G', NOW(), NOW()),
('John Doe', 'john@klumusic.com', '$2a$10$7xvq.CqZlJgd1tYy1JlYt.QdVyj1P0xZ1rKzL.qGcvhtCzrfJ.j6G', NOW(), NOW()),
('Jane Smith', 'jane@klumusic.com', '$2a$10$7xvq.CqZlJgd1tYy1JlYt.QdVyj1P0xZ1rKzL.qGcvhtCzrfJ.j6G', NOW(), NOW());

-- Sample artists
INSERT INTO artists (name, bio, created_at, updated_at) VALUES
('The Weeknd', '', NOW(), NOW()),
('Ed Sheeran', '', NOW(), NOW());

-- Sample albums
INSERT INTO albums (title, artist_id, release_year, cover_url, created_at, updated_at) VALUES
('After Hours', 1, 2020, '', NOW(), NOW()),
('Divide', 2, 2017, '', NOW(), NOW());

-- Sample songs
INSERT INTO songs (title, artist_id, album_id, duration_seconds, file_path, cover_url, play_count, created_at, updated_at) VALUES
('Blinding Lights', 1, 1, 200, '', '', 0, NOW(), NOW()),
('Shape of You', 2, 2, 233, '', '', 0, NOW(), NOW());
