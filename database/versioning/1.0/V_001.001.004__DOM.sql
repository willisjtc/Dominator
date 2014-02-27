CREATE PROCEDURE new_user(usrnm VARCHAR(50), pswd VARCHAR(50), desc VARCHAR(50), image VARCHAR(200))
MODIFIES SQL DATA
BEGIN ATOMIC
    INSERT INTO dominion_user (username, password, description, user_image) VALUES
    (usrnm, pswd, desc, image);
END