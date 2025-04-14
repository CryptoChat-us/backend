package br.com.api.crypto_chat.feature.Thirdparties.OpenAI.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SpeechRequestVO {
    private String model;
    private String input;
    private String voice;
}
