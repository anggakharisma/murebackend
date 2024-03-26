package com.murebackend.murebackend.Song;

import javax.validation.constraints.NotNull;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@RequiredArgsConstructor
class SongRequest extends Song {
	private Long albumId;	

	private Long artistId;	
}
