INSERT INTO tb_especialidades (id, nome, descricao, imagem_url) VALUES ('e1000000-0000-0000-0000-000000000001', 'Cardiologia', 'Especialidade responsável pelo diagnóstico e tratamento de doenças do coração e sistema cardiovascular.', 'https://www.clinicasalute.com.br/upload/service/H44iBhjj04GNo8o6AiDauoRfXuwyRzzfD66eXeVK.jpeg');
INSERT INTO tb_especialidades (id, nome, descricao, imagem_url) VALUES ('e1000000-0000-0000-0000-000000000002', 'Dermatologia', 'Especialidade voltada para prevenção, diagnóstico e tratamento de doenças da pele.', 'https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRQvFQAEPqdO_Da6nfB1FI9WhEX_D5zDv2bw4bP-fVpHhflkysPQ0Sz5UE&s=10');
INSERT INTO tb_especialidades (id, nome, descricao, imagem_url) VALUES ('e1000000-0000-0000-0000-000000000003', 'Pediatria', 'Especialidade médica dedicada ao acompanhamento da saúde de crianças e adolescentes.', 'https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSVLlP_i_tn7-m2MiA71XxEpSDiqoA9IUsUZqGXjv615A&s=10');
INSERT INTO tb_especialidades (id, nome, descricao, imagem_url) VALUES ('e1000000-0000-0000-0000-000000000004', 'Ortopedia', 'Especialidade responsável pelo tratamento de ossos, músculos, articulações e ligamentos.', 'https://multimedical.com.br/wp-content/uploads/2022/10/ortopedia.png');
INSERT INTO tb_especialidades (id, nome, descricao, imagem_url) VALUES ('e1000000-0000-0000-0000-000000000005', 'Neurologia', 'Especialidade que trata doenças do sistema nervoso central e periférico.', 'https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQY9oxPEkYFQizDTU23imwwziPJzRW49TWZHJDVEvEEhZtZvsVSHKlkkQw&s=10');
INSERT INTO tb_especialidades (id, nome, descricao, imagem_url) VALUES ('e1000000-0000-0000-0000-000000000006', 'Oftalmologia', 'Especialidade voltada para o cuidado da visão e tratamento dos olhos.', 'https://oftalmocitta.com.br/wp-content/uploads/2025/07/oftalmologia-geral.webp');
INSERT INTO tb_especialidades (id, nome, descricao, imagem_url) VALUES ('e1000000-0000-0000-0000-000000000007', 'Ginecologia', 'Especialidade dedicada à saúde do sistema reprodutor feminino.', 'https://femmesante.com.br/wp-content/uploads/2023/10/femmesante-ginecologia.jpg');
INSERT INTO tb_especialidades (id, nome, descricao, imagem_url) VALUES ('e1000000-0000-0000-0000-000000000008', 'Psiquiatria', 'Especialidade focada na prevenção, diagnóstico e tratamento de transtornos mentais.', 'https://images.unsplash.com/photo-1573497620053-ea5300f94f21');
INSERT INTO tb_especialidades (id, nome, descricao, imagem_url) VALUES ('e1000000-0000-0000-0000-000000000009', 'Endocrinologia', 'Especialidade responsável pelo tratamento de distúrbios hormonais e metabólicos.', 'https://images.unsplash.com/photo-1579684385127-1ef15d508118');
INSERT INTO tb_especialidades (id, nome, descricao, imagem_url) VALUES ('e1000000-0000-0000-0000-000000000010', 'Urologia', 'Especialidade que trata doenças do trato urinário e sistema reprodutor masculino.', 'https://feliciorocho.org.br/media/219/urologia.jpg');


INSERT INTO tb_usuarios (id, nome, email, senha, tipo_usuario) VALUES ('4d2e8b1c-9a3f-4e6c-b715-2d83fa096e21', 'Lionel Messi', 'c@1', '$2a$12$N6Uy0gNrc.Nwi6ZdmYLYdu4J9wE3ymmT0Ipk.13Wo/rp/1L8DuL12', 'paciente');
INSERT INTO tb_pacientes (usuario_id) VALUES ('4d2e8b1c-9a3f-4e6c-b715-2d83fa096e21');

INSERT INTO tb_usuarios (id, nome, email, foto, senha, tipo_usuario) VALUES ('bdfc1043-b7bc-413b-8a11-80348a9936a4', 'Marcelo Balbino', 'm@g', 'https://res.cloudinary.com/mgw0uzkh/image/upload/v1789669849/fotos_minha_API/om07exhniubcmyx3e1rm.png', '$2a$12$Ex03TjDSyCMUc6QvSCIIgODqQKhcWDr2YbXhWSuNH6aVucMuZzGX2', 'medico');
INSERT INTO tb_medicos (usuario_id, crm, uf_emissao, sobre_mim, formacao_academica) VALUES ('bdfc1043-b7bc-413b-8a11-80348a9936a4', '123456', 'SP', 'Médico com ampla experiência em teleconsultas.', 'Graduação pela USP (2017) - Pós graduação pela UNICAMP (2020) - Mestrado pela UFMG (2025)');

INSERT INTO tb_medicos_especialidades (id, medico_id, especialidade_id) VALUES ('a7b3c2e1-8f49-410a-bd62-59c8d17a3f5b', 'bdfc1043-b7bc-413b-8a11-80348a9936a4', 'e1000000-0000-0000-0000-000000000007');

INSERT INTO tb_horarios(id, medico_id, dia, manha, tarde, noite) VALUES ('11111111-1111-1111-1111-111111111111', 'bdfc1043-b7bc-413b-8a11-80348a9936a4', 'segunda', true, false, true);

INSERT INTO tb_consultas (id, data_hora, motivo_cancelamento, observacoes, link_consulta, status, paciente_id, medico_id, especialidade_id) VALUES ('consulta-teste-001', '2026-10-10T14:00:00', '', 'Paciente relatou melhora dos sintomas.', 'https://meet.google.com/abc-defg-hij', 'finalizada', '4d2e8b1c-9a3f-4e6c-b715-2d83fa096e21', 'bdfc1043-b7bc-413b-8a11-80348a9936a4', 'e1000000-0000-0000-0000-000000000007');
INSERT INTO tb_consultas (id, data_hora, motivo_cancelamento, observacoes, link_consulta, status, paciente_id, medico_id, especialidade_id) VALUES ('consulta-teste-002', '2026-10-12T10:30:00', '', 'Consulta de acompanhamento.', 'https://meet.google.com/klm-nopq-rst', 'finalizada', '4d2e8b1c-9a3f-4e6c-b715-2d83fa096e21', 'bdfc1043-b7bc-413b-8a11-80348a9936a4', 'e1000000-0000-0000-0000-000000000007');

INSERT INTO tb_avaliacoes (id, comentario, nota, data, consulta_id) VALUES ('avaliacao-teste-001', 'Excelente atendimento, médico muito atencioso e esclareceu todas as minhas dúvidas.', 5.0, '2026-10-10T14:45:00', 'consulta-teste-001');
INSERT INTO tb_avaliacoes (id, comentario, nota, data, consulta_id) VALUES ('avaliacao-teste-002', 'Bom atendimento, explicou o tratamento com clareza e demonstrou profissionalismo.', 4.0, '2026-10-12T11:15:00', 'consulta-teste-002');

CREATE INDEX idx_consultas_paciente ON tb_consultas(paciente_id);
CREATE INDEX idx_consultas_medico ON tb_consultas(medico_id);
CREATE INDEX idx_consultas_data_status ON tb_consultas(data_hora, status);
CREATE INDEX idx_avaliacoes_consulta ON tb_avaliacoes(consulta_id);