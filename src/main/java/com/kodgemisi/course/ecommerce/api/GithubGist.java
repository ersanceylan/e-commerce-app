package com.kodgemisi.course.ecommerce.api;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
class GithubGist implements Serializable {

    private String id;

    private String description;

}
