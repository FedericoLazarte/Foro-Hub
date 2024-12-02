alter table topics
alter column creation_date type timestamp
using creation_date::timestamp;

update topics
set creation_date = creation_date + interval '12 hour'
where creation_date::time = '00:00:00';