package com.xccaia.user.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

public class PageableQueryDTO {

  /**
   * 起始页，默认1
   */
  private Integer pageNum = 1;

  /**
   * 每页记录数，默认10
   */
  private Integer pageSize = 2;

  /**
   * 是否包含count查询，默认包含
   */
  @JsonIgnore
  @JsonProperty(access = Access.READ_ONLY)
  private Boolean count = Boolean.TRUE;

  /**
   * 是否分页合理化，默认是
   */
  @JsonIgnore
  @JsonProperty(access = Access.READ_ONLY)
  private Boolean reasonable = Boolean.TRUE;

  /**
   * 是否查询全部数据，默认否
   */
  @JsonIgnore
  @JsonProperty(access = Access.READ_ONLY)
  private Boolean pageSizeZero = Boolean.FALSE;

  public Integer getPageNum() {
    return pageNum;
  }

  public PageableQueryDTO setPageNum(Integer pageNum) {
    this.pageNum = pageNum;
    return this;
  }

  public Integer getPageSize() {
    return pageSize;
  }

  public PageableQueryDTO setPageSize(Integer pageSize) {
    this.pageSize = pageSize;
    return this;
  }

  public Boolean getCount() {
    return count;
  }

  public PageableQueryDTO setCount(Boolean count) {
    this.count = count;
    return this;
  }

  public Boolean getReasonable() {
    return reasonable;
  }

  public PageableQueryDTO setReasonable(Boolean reasonable) {
    this.reasonable = reasonable;
    return this;
  }

  public Boolean getPageSizeZero() {
    return pageSizeZero;
  }

  public PageableQueryDTO setPageSizeZero(Boolean pageSizeZero) {
    this.pageSizeZero = pageSizeZero;
    return this;
  }
}
