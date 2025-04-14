package br.com.api.crypto_chat.vo;

import java.util.List;

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
public class ChatRequestVO {

    private String model;
    private List<MessageVO> messages;
    @Builder.Default
    private Double temperature = 0.7;

}
