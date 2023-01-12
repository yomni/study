package study.yomni.springbatch.domain;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@EqualsAndHashCode
public class Coffee {
    private String brand;
    private String origin;
    private String characteristics;
}
