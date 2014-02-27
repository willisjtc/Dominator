CREATE PROCEDURE create_game(gameCreator BIGINT, gameModel VARCHAR(32768), INOUT filePath VARCHAR(512), OUT gameId BIGINT)
MODIFIES SQL DATA
BEGIN ATOMIC
	DECLARE temp_game_id BIGINT;
	DECLARE full_file_path VARCHAR(512);
	INSERT INTO game (creator) VALUES (gameCreator);
	SET temp_game_id = IDENTITY();
	SET gameId = temp_game_id;
	SET filePath = CONCAT(filePath, temp_game_id);
	CALL insert_game_history (temp_game_id, filePath);
END;