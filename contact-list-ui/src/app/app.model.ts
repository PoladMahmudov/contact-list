export interface User {
  name: string;
  avatarUrl: string;
}

export interface Page<D> {
  content: D[];

  /**
   * Available total content elements
   */
  totalElements: number;
  /**
   * Available page numbers
   */
  totalPages: number;
  /**
   * Page size
   */
  size: number;
  /**
   * Page number
   */
  number: number;
}
