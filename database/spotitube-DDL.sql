-- we don't know how to generate schema spotitube (class Schema) :(
create table accounts
(
	user varchar(255) not null
		primary key,
	password varchar(255) null,
	full_name varchar(255) null
)
  engine = InnoDB
;

create table playlists
(
  playlist_id int auto_increment,
  name        varchar(100) null,
  owner       varchar(255) null,
	constraint all_playlists_playlist_id_uindex
  unique (playlist_id),
	constraint ownder___fk
  foreign key (owner) references accounts (user)
)
  engine = InnoDB;

alter table playlists
  add primary key (playlist_id)
;

create table tokens
(
	user varchar(255) not null
		primary key,
	token varchar(255) null,
	valid_until varchar(255) null,
	constraint tokens_accounts_user_fk
  foreign key (user) references accounts (user)
)
  engine = InnoDB
;

create table tracks
(
  track_id          int auto_increment
		primary key,
  title             varchar(255) null,
  performer         varchar(255) null,
  duration          int          null,
  album             varchar(255) null,
  playcount         int          null,
  publicationDate   date         null,
  description       varchar(255) null,
  offline_available tinyint(1)   null
)
  engine = InnoDB
;

create table playlist_track_connector
(
	playlist_id int not null,
	track_id int not null,
	primary key (playlist_id, track_id),
	constraint playlist_track_connector_playlist_id___fk
  foreign key (playlist_id) references playlists (playlist_id),
	constraint playlist_track_connector_track_id___fk
  foreign key (track_id) references tracks (track_id)
)
  engine = InnoDB
;

