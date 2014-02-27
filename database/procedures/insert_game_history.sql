CREATE PROCEDURE insert_game_history(gameId BIGINT, INOUT filePath VARCHAR(512))
MODIFIES SQL DATA
BEGIN ATOMIC
	DECLARE date_added TIMESTAMP;
	SET date_added = CURRENT_TIMESTAMP;
	SET filePath = CONCAT(CONCAT(filePath, '/'), date_added);
	INSERT INTO game_history (game_id, game_file, date_created) VALUES (gameId, filePath, date_added);
END;