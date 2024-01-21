ALTER TABLE medicos
ADD COLUMN ativo Boolean;
UPDATE medicos set ativo = true;